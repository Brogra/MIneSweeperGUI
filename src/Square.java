public class Square {

    private int Touching;
    private int xLoc;
    private int yLoc;
    private boolean Hidden;
    private boolean Clicked;
    private boolean isMine;
    private boolean Test;


    public Square(int MinesTouching, int xCord, int yCord, boolean hide,boolean click,boolean mine,boolean tested){
        Touching = MinesTouching;
        xLoc = xCord;
        yLoc = yCord;
        Hidden = hide;
        Clicked = click;
        isMine = mine;
        Test = tested;
    }
    public void setTest(boolean set){
        Test = set;
    }
    public boolean didTest(){
        return Test;
    }
    public boolean isHidden(){
        return Hidden;
    }

    public void firstClick(){
        isMine = false;

    }
    public void click(){
        Clicked = true;
        Hidden = false;
    }
    public int[] getXAndY(){
        int[] loc = {xLoc,yLoc};
        return loc;
    }
    public int[] info(){
        int[] info = {xLoc,yLoc,Touching};
        Hidden = false;
        if(isMine){
            info[0] = 42;
        }
        return info;
    }
    public boolean isMine(){
        return isMine;
    }
    public boolean notHidden(){
        if(!Hidden){
            return  true;
        }
        return false;
    }
    public void setMine(){
        isMine = true;
    }
    public void revealSpot(){
            Hidden = false;

    }
    public void updateTouch(int value){
        Touching = value;
    }
    public boolean didClick(){
        return Clicked;
    }
    public int intTouching(){
        return Touching;
    }
    public String touching(){
        return String.valueOf(Touching);
    }
    public String icon() {
        if (!Clicked){
            return "#";
        }
        if (Clicked && !isMine) {
            return String.valueOf(Touching);
        }else if(isMine && Clicked){
            return "+";
        }else if(!Hidden){
            return String.valueOf(Touching);
        }
        return "#";
    }
    public void setXCord(int x){
        xLoc = x;
    }
    public void setYCord(int y){
        yLoc = y;
    }


}
