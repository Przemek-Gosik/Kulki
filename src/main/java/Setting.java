public class Setting {
    private Level level;
    private int platformWidth;
    private int numberOfBalls;
    private int ballSpeed;

    private int numberOfRows;

    public Setting(Level level) {
        this.level = level;
        this.platformWidth=120;
        this.ballSpeed=3;
        this.numberOfBalls=1;
        this.numberOfRows=1;
        switch (this.level){
            case Easy:
                platformWidth-=20;
                numberOfRows++;
                break;
            case Medium:
                platformWidth-=40;
                numberOfRows+=2;
                numberOfBalls++;
                break;
            case Hard:
                platformWidth-=60;
                numberOfRows+=3;
                numberOfBalls+=2;
                break;
            case VeryHard:
                platformWidth-=70;
                numberOfBalls+=3;
                numberOfRows+=4;
                break;


        }
    }

    public Level getLevel() {
        return level;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
