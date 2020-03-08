import com.exercise.event.AbstractDomainEvent;
import com.exercise.event.DomainEventBusHolder;
import com.exercise.event.handler.DomainEventHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainEventBusTest {
    private DomainEventBusHolder domainEventBusHolder;

    @Before
    public void setUp() throws Exception {
//        this.domainEventBus = new DefaultDomainEventBus();
    }

    @After
    public void tearDown() throws Exception {
//        this.domainEventBus = null;
    }

    @Test
    public void publishTest(){
        // 创建事件处理器
        TestEventHandler eventHandler = new TestEventHandler();
        // 注册事件处理器
        DomainEventBusHolder.getHandlerRegistry().register(TestEvent.class, eventHandler);

        // 发布事件
        DomainEventBusHolder.getPubliser().publish(new TestEvent("123"));

        // 检测事件处理器是够运行
        Assert.assertEquals("123", eventHandler.data);
    }

    class TestEvent extends AbstractDomainEvent {
        private String data;

        TestEvent(String data) {
            this.data = data;
        }

        String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    class TestEventHandler implements DomainEventHandler<TestEvent> {
        private String data;
        @Override
        public void handle(TestEvent event) {
            this.data = event.getData();
        }
    }

    @Test
    public void test(){
        int[] nums = {0,0,1,1,2,2};
        int len = nums.length;
        int m = 0;
        for(int i = 0;i < len ; i++){
            if (nums[m] != nums[i]){
                nums[++m] = nums[i];
            }
        }

        for (int i = 0;i<m ;i++){
            System.out.println(nums[i]);
        }
    }
}
