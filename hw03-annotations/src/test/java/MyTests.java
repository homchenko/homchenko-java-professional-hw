class MyTests {

    @Before
    public void setUp() {
        System.out.println("Setting up...");
    }

    @Test
    public void test1() {
        System.out.println("Running test 1...");
        // Simulate a test failure
        if (1 == 1) throw new RuntimeException("Test 1 failed");
    }

    @Test
    public void test2() {
        System.out.println("Running test 2...");
        // Simulate a successful test
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down...");
    }
}
