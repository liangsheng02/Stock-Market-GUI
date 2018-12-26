import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;

/**
 * This class is a panel that draws line graph for the the input ArrayList.
 * If user moves the mouse in the panel, the nearest point would be highlighted,
 * and the details of the data point would be showed on the line graph.
 * (Actually, a resource-saving way to do this is applying JLayeredPane in the JFrame,
 *  the panel behind shows the line graph, and sends params to a transparent panel on the top,
 *  the transparent panel draws dotted lines and show the nearest point by repaint.
 *  But I think it is not very necessary in this little project.)
 * @author Sheng Liang
 */
public class DrawStock extends JPanel implements MouseMotionListener {

    private Graphics2D g2;
    private int pad = 30;
    private int labelPad = 20;
    private Color grapColor = new Color(38, 41, 43, 255);
    private Color backgroundColor = new Color(60, 63, 65, 255);
    private Color pointColor = new Color(186, 186, 186, 180);
    private Color gridColor = new Color(136, 138, 141, 200);
    private Color lineColor = new Color(186, 186, 186, 120);
    private Color riseColor = new Color(0, 255, 0, 120);
    private Color fallColor = new Color(255, 0, 0, 120);
    private Color dottedColor = new Color(186, 186, 186, 80);
    private Color stringColor = new Color(186, 186, 186, 180);
    private Color labelColor = new Color(186, 186, 186, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 8;
    private int yDivision = 10;
    private ArrayList<Double> data;
    private ArrayList<String> date;
    private ArrayList<Point> graphPoints;
    private Stroke oldStroke;
    private int i ;
    private Double xScale;
    private Double yScale;
    private Point nearestP;
    private Point nearestX;
    private Point nearestY;
    private String textX;
    private String textY;
    private Double min;
    private Double max;

    /**
     * This method finds the nearest point by x axis.
     * @param x the mouse location on x axis.
     * */
    private Point getNearest(int x) {
        int dis = Integer.MAX_VALUE;
        Point nearestP = new Point();
        for (Point point : graphPoints) {
            if (Math.abs(point.x-x) < dis) {
                dis = Math.abs(point.x-x);
                nearestP = point;
            }
        }
        return nearestP;
    }

    /**
     * This method sends some new data to DrawStock object, and applies repaint method to redraw the line graph.
     * @param data a different ArrayList<Double>
     * */
    public void setData(ArrayList<Double> data) {
        this.data = data;
        this.max = StaticMethods.getMaxData(this.data);
        this.min = StaticMethods.getMinData(this.data);
        this.nearestP=this.nearestX=this.nearestY= new Point(0,0);
        invalidate();
        this.repaint();
    }

    /**
     * Constructor, initialise data, date and mouse points, and add the MouseMotionListener.
     * @param data ArrayList of a stock data, such as Open, Close and Volume.
     * @param date ArrayList of date
     * */
    public DrawStock(ArrayList<Double> data, ArrayList<String> date) {
        this.data = data;
        this.date = date;
        this.max = StaticMethods.getMaxData(data);
        this.min = StaticMethods.getMinData(data);
        this.addMouseMotionListener(this);
        this.setBackground(backgroundColor);

        //initialise the nearest point of the mouse.
        this.nearestP=this.nearestX=this.nearestY= new Point(0,0);
    }

    /**
     * This method applies Graphics2d to draw the line graph.
     * */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //create graphPoints ArrayList to store data points;
        xScale = ((double) getWidth() - 2*pad - labelPad) / (data.size() - 1);
        yScale = ((double) getHeight()- 2*pad - labelPad) / (1.1*max - 0.8*min);
        graphPoints = new ArrayList<>();
        Iterator dataIterator = data.iterator();
        i = 0;
        while (dataIterator.hasNext()) {
            Double d = (Double) dataIterator.next();
            int x1 = (int) (i * xScale + pad + labelPad);
            int y1 = (int) ((1.1*max - d) * yScale + pad);
            graphPoints.add(new Point(x1, y1));
            i++;
        }

        //draw background
        g2.setColor(grapColor);
        g2.fillRect(pad + labelPad, pad,getWidth() - 2*pad - labelPad,getHeight() - 2*pad - labelPad);

        //draw x and y boundaries
        g2.setColor(labelColor);
        g2.drawLine(pad + labelPad, getHeight() - pad - labelPad,
                pad + labelPad, pad);
        g2.drawLine(getWidth() - pad, getHeight() - pad - labelPad,
                getWidth() - pad, pad);
        g2.drawLine(pad + labelPad, getHeight() - pad - labelPad,
                getWidth() - pad, getHeight() - pad - labelPad);

        //draw grid lines for y axis.
        i = 0;
        while (i <= yDivision) {
            int x0 = pad + labelPad - 3;
            int y0 = getHeight() - ((i * (getHeight() - pad * 2 - labelPad)) / yDivision + pad + labelPad);
            if (data.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(pad + labelPad, y0, getWidth() - pad, y0);
                g2.setColor(labelColor);
                String yLabel = ((int) ((0.8*min + (1.1*max - 0.8*min) * ((i * 1.0) / yDivision)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
                i++;
            }
        }

        //draw lines graph and charts
        oldStroke = g2.getStroke();
        g2.setStroke(GRAPH_STROKE);
        i = 0;
        while (i < graphPoints.size() - 1) {
            //draw lines!
            g2.setColor(lineColor);
            g2.drawLine(graphPoints.get(i).x, graphPoints.get(i).y,
                    graphPoints.get(i+1).x, graphPoints.get(i+1).y);
            //draw charts!
            if (graphPoints.get(i).y - graphPoints.get(i+1).y >= 0) {//raise
                g2.setColor(riseColor);
                g2.fillRect(graphPoints.get(i).x, graphPoints.get(i+1).y,
                        graphPoints.get(i+1).x - graphPoints.get(i).x,
                        graphPoints.get(i).y - graphPoints.get(i+1).y);
            }
            else{//fall
                g2.setColor(fallColor);
                g2.fillRect(graphPoints.get(i).x, graphPoints.get(i).y,
                        graphPoints.get(i+1).x - graphPoints.get(i).x,
                        graphPoints.get(i+1).y - graphPoints.get(i).y);
            }
            i++;
        }//can't use Iterator here because it has to get the i+1 element

        //if user moves the mouse, nearestP would be updated to >0, then redraw the graph.
        if(this.nearestP.x != 0){
            //draw strings
            g2.setColor(stringColor);
            g2.drawString(textX, this.nearestX.x-23, this.nearestX.y + g2.getFontMetrics().getHeight());//x axis label
            g2.drawString(textY, this.nearestY.x, this.nearestY.y);//y axis label
            //draw points
            g2.setStroke(oldStroke);
            g2.setColor(pointColor);
            g2.fillOval(nearestP.x - pointWidth / 2, nearestP.y - pointWidth / 2, pointWidth, pointWidth);
            //draw dotted lines
            g2.setColor(dottedColor);
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{8,12},0));
            g2.drawLine(this.nearestX.x, this.nearestX.y, this.nearestP.x, this.nearestP.y);//to x axis
            g2.drawLine(this.nearestY.x, this.nearestY.y, this.nearestP.x, this.nearestP.y);//to y axis
        }
    }

    /**
     * Implementation of the MouseMotionListener.
     * Apply repaint method to redraw the graph when user moves the mouse.
     * */
    @Override
    public void mouseDragged(MouseEvent e) {}//do nothing!
    @Override
    public void mouseMoved(MouseEvent e) {
        Graphics g = this.getGraphics();nearestP = getNearest(e.getX());
        i = graphPoints.indexOf(nearestP);
        textY = data.get(i) + "";
        textX = date.get(i) + "";
        nearestX = new Point(nearestP.x,getHeight() - pad - labelPad);
        nearestY = new Point(pad + labelPad, nearestP.y);
        this.repaint();
    }

    /**
     * Main method for testing
     * */
    public static void main(String[] args) throws IOException {
        DataRetriever dr = new DataRetriever("AAPL", "01/01/2018", "12/31/2018");
        StockData stockData = dr.getStockData();
        ArrayList<Double> openList = stockData.getOpenList();
        ArrayList<String> dateList = stockData.getDateList();
        DrawStock drawStock = new DrawStock(openList, dateList);
        drawStock.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(drawStock);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}