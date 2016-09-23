package okapi.bean;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import org.folio.okapi.bean.Ports;
import org.junit.Assert;
import org.junit.Test;

public class PortsTest extends Assert {

  @Test
  public void createAndGet() {
    Ports ports = new Ports(1, 10);
    for (int i = 1; i < 10; i++)
      assertThat("Port out of range!", ports.get(), allOf(greaterThan(0), lessThan(10)));
    assertEquals("Too many ports in stock!", -1, ports.get());
  }

  @Test
  public void cantFreeDuplicatePort() {
    Ports ports = new Ports(1, 10);
    assertEquals(9, ports.size());
    ports.free(5);
    assertEquals("Freed a duplicate port!", 9, ports.size());
  }
}
