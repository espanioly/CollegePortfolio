import components.map.Map;
import components.map.Map2;

/**
 * Customized JUnit test fixture for {@code Map4} using default constructor.
 */
public class Map4Test extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map2<String, String>();
    }

}
