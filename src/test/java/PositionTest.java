import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void getX(){
        Position p = new Position(1,2);
        Assertions.assertEquals(1,p.getX());
    }

    @Test
    public void getY(){
        Position p = new Position(1,2);
        Assertions.assertEquals(2,p.getY());
    }
}
