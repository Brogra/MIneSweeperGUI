import javax.swing.*;
import java.awt.image.ImageObserver;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class main {
    final static int SIZE = 8;
    final static int BEGMIN = 5;
    static Random rand = new Random();
    static Scanner read = new Scanner(System.in);
    static boolean firstClick = true;
    private static JFrame frame;
    private static int boxSize = 50;
    private static JPanel pane;
    private static int buffer = 10;
    private static final int HEIGHT = (boxSize + buffer) * SIZE;
    private static final int WIDTH = (boxSize + buffer) * SIZE;
    private static final FlowLayout flowLayout = new FlowLayout();




    public static void initJFRAME(ArrayList<Square> squares){
        frame = new JFrame("MineSweepers");
        pane = new JPanel();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        pane.setLayout(flowLayout);
        pane.setVisible(true);
        pane.setBackground(Color.blue);

        for (int i = 0; i <squares.size() ; i++) {

            JButton temp = squares.get(i).getButton();
            temp.setBackground(Color.gray);
            temp.setPreferredSize(new Dimension(boxSize,boxSize));
            int touch = squares.get(i).intTouching();
            Square s = squares.get(i);
            int[] cord = squares.get(i).getXAndY();
            temp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String value = String.valueOf(touch);
                    temp.setText(value);
                    update(cord,squares);
                }
            });
            pane.add(temp);

        }
        frame.add(pane);




    }


    public static ArrayList<Square> setMines(ArrayList<int[]> locs, ArrayList<Square> squaresObj){
        System.out.println("     " + locs.get(0)[0] + " " +locs.get(0)[1]);
        System.out.println(locs.get(1)[0] + " " +locs.get(1)[1]);
        for (int x = 0 ; x < locs.size();x++){
            for (int i = 0; i < squaresObj.size(); i++) {
                if(Arrays.equals(squaresObj.get(i).getXAndY(),(locs.get(x)))){
                    squaresObj.get(i).setMine();
                }
            }
        }
        return squaresObj;
    }

    public static ArrayList<int[]> mineLoc(){
        ArrayList<int[]> Locations = new ArrayList<>();

        int[] place = new int[2];
        int x;
        int[] clone;

        for (int e = 0; e < BEGMIN; e++) {

            clone = place.clone();
            x = rand.nextInt(SIZE)+ 1;
            clone[0] = x;
            x = rand.nextInt(SIZE)+ 1;
            clone[1] = x;
            Locations.add(e,clone);
        }
        for (int[] pair: Locations) {
            System.out.println("ML: " + pair[0] + " " + pair[1]);
        }
        return Locations;
    }

    public static void Draw(ArrayList<Square> squaresObj){
        for (int i = 0; i < SIZE * 2 + 1; i++) {
            System.out.print(" ");

        }
        System.out.println("X Col");
        System.out.println("        ___________");
        System.out.print("         ");


        for (int i = 1; i < SIZE + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int place = 0;
        for(int i = 1; i < SIZE + 1;i++){
            System.out.print("Y Row " + i + "[ ");
            for (int e = 1; e < SIZE + 1; e++) {
                squaresObj.get(place).setXCord(e);
                squaresObj.get(place).setYCord(i);

                System.out.print(squaresObj.get(place).icon()+ " ");
                place++;
            }
            System.out.println();
        }

    }

    public static ArrayList<Square> createBoxes(){
        ArrayList<Square> squares = new ArrayList<Square>();
        //String[] names = new String[SIZE*SIZE];
        for (int d = 0; d < SIZE*SIZE;d++) {
            JButton tempB = new JButton();
            tempB.setBackground(Color.gray);
            Square a = new Square(0,0,0, true,false,false,false, tempB);
            squares.add(a);
        }

        int p = 0;
        for (int i = 1; i < (SIZE) + 1 ; i++) {
            for (int e = 1; e < SIZE + 1; e++) {
                squares.get(p).setXCord(e);
                squares.get(p).setYCord(i);
                p++;
            }
        }
        return squares;
    }

    public static int[] getMove(){
        System.out.println();
        System.out.println("Enter Square Cords in form \"xCord yCord\"");
        int[] cords = new int[2];
        cords[0] = read.nextInt();
        cords[1] = read.nextInt();
        return cords;
    }

    public static boolean WON(ArrayList<Square> squaresObj){
        int total = 0;
        for (Square x:squaresObj) {
            if(x.isMine() && !x.isHidden()){
                return true;
            }
            if(!x.isHidden() && x.notHidden()){
                total++;
            }
        }
        if (total == (SIZE * SIZE) - BEGMIN) {
            return true;
        }
        return false;
    }

    public static ArrayList<Square> update(int[] cords, ArrayList<Square> squaresObj) {
        main run = new main();
        //squaresObj.get(findIndex(cords,squaresObj)).click();
        ArrayList<Square> clone = new ArrayList<Square>();
        clone = (ArrayList<Square>) squaresObj.clone();
        if(cords[0] < 1 || cords[0] > SIZE || cords[1] < 1 || cords[1] > SIZE){
            return clone;
        }
        if(clone.get(findIndex(cords,clone)).intTouching() == 0 && clone.get(findIndex(cords,clone)).isHidden()){


            clone.get(findIndex(cords,clone)).click();
            JButton temp = clone.get(findIndex(cords,clone)).getButton();
            temp.setText(String.valueOf(clone.get(findIndex(cords,clone)).intTouching()));
            squaresObj = clone;
            ArrayList<int[]> soourond = new ArrayList<>();
            soourond = getSurrounding(cords);
            update(soourond.get(0), clone);
            update(soourond.get(1),clone);
            update(soourond.get(2),clone);
            update(soourond.get(3),clone);
            update(soourond.get(4),clone);
            update(soourond.get(5),clone);
            update(soourond.get(6),clone);
            update(soourond.get(7),clone);


        }else{
            if(clone.get(findIndex(cords,clone)).icon().equals("+")){

            }else{
                clone.get(findIndex(cords,clone)).click();
                JButton temp = clone.get(findIndex(cords,clone)).getButton();
                temp.setText(String.valueOf(clone.get(findIndex(cords,clone)).intTouching()));
            }

            return clone;
        }
        return clone;
    }

    public static int istouch(int[] cords, ArrayList<Square> obj) {
        int touching = 0;
        if (cords[0] + 1 <= SIZE) {
            int[] newCord = {cords[0] + 1, cords[1]};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }

                }

            }
        }
        if (cords[0] - 1 > 0) {
            int[] newCord = {cords[0] - 1, cords[1]};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }


                }

            }
        }

        if (cords[1] - 1 > 0) {
            int[] newCord = {cords[0], cords[1] - 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }

        if (cords[1] + 1 > 0) {
            int[] newCord = {cords[0], cords[1] + 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }

        if (cords[1] - 1 > 0) {
            int[] newCord = {cords[0] - 1, cords[1] - 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }

        if (cords[1] - 1 > 0) {
            int[] newCord = {cords[0] - 1, cords[1] + 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }

        if (cords[1] - 1 > 0) {
            int[] newCord = {cords[0] + 1, cords[1] + 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }

        if (cords[1] - 1 > 0) {
            int[] newCord = {cords[0] + 1, cords[1] - 1};
            for (Square spot : obj) {
                if (Arrays.equals(spot.getXAndY(), (newCord))) {
                    if (spot.isMine()) {
                        touching++;
                    }
                }
            }
        }
        return touching;
    }

    public static  ArrayList<Square> touching(Square y, ArrayList<Square> obj){
        int[] cords = y.getXAndY();
        int spot = 0;
        for (Square x:obj) {
            if (x.equals(y)){
                x.updateTouch(istouch(x.getXAndY(),obj));
            }
            spot++;
        }
        return obj;

    }

    public static boolean didLose(ArrayList<Square> squaresObj){
        for (Square x:squaresObj) {
            if(x.isMine() && x.notHidden()){
                return true;
            }
        }
        return false;
    }

    public static int findIndex(int[] cords, ArrayList<Square> obj){
        int place  = 0;
        for(Square i : obj){
            if (Arrays.equals(i.getXAndY(), cords)){

                return place;
            }
            place++;
        }
        return place;
    }

    public static  ArrayList<int[]> getSurrounding(int[] cords){
        int[] s1 = cords;
        int[] s2 = {cords[0] - 1, cords[1] - 1};
        int[] s3 = {cords[0], cords[1] - 1};
        int[] s4 = {cords[0] + 1, cords[1] - 1};
        int[] s5 = {cords[0] - 1, cords[1]};
        int[] s6 = {cords[0] + 1, cords[1]};
        int[] s7 = {cords[0] - 1, cords[1] + 1};
        int[] s8 = {cords[0], cords[1]  + 1};
        int[] s9 = {cords[0] + 1 , cords[1] + 1};
        ArrayList<int[]> gone= new ArrayList<>();
        gone.add(s1);
        gone.add(s2);
        gone.add(s3);
        gone.add(s4);
        gone.add(s5);
        gone.add(s6);
        gone.add(s7);
        gone.add(s8);
        gone.add(s9);
        return gone;

    }

    public static ArrayList<Square> setvalues(ArrayList<Square> objts){
        for (int i = 0; i <objts.size() ; i++) {
            objts = touching(objts.get(i),objts);
        }
        return objts;
    }

    public ArrayList<Square> firstClick(int[] cord, ArrayList<Square> obj){
        if(firstClick){
            obj.get(findIndex(cord,obj)).firstClick();
            firstClick = false;
        }
        return obj;
    }
    public static void main(String args[]){
        ArrayList<Square> squaresObj = createBoxes();
        ArrayList<int[]> mines = mineLoc();
        squaresObj = setMines(mines,squaresObj);
        squaresObj = setvalues(squaresObj);

        initJFRAME(squaresObj);
        boolean won = false;



        int[] cords;
        while(!won){
            Draw(squaresObj);
            cords = getMove();
            update(cords,squaresObj);
            //won = didLose(squaresObj);
            won = WON(squaresObj);
        }

        Draw(squaresObj);
        if(didLose(squaresObj)){
            System.out.println("You Lost");

        }else{
            System.out.println("You Won");

        }


    }
}
