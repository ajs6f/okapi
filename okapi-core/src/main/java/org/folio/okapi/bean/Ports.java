package org.folio.okapi.bean;

import static java.util.stream.IntStream.range;

import java.util.ArrayList;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Manages a list of available ports.
 * When a module is deployed, a new port may be allocated for it from this list.
 *
 */
public class Ports extends ArrayList<Integer> {

  private final Logger logger = LoggerFactory.getLogger("Ports");

  public Ports(int port_start, int port_end) {
    super(port_end - port_start);
    range(port_start, port_end).forEach(this::add);
  }

  /**
   * Allocate a port.
   * @return the newly allocated port number, or -1 if none available
   */
  public int get() {
    return isEmpty() ? -1 : remove(size()-1);
  }

  /**
   * Release a previously allocated port.
   * @param p The port to release.
   */
  public void free(int p) {
    if (p > 0 && !contains(p)) {
      logger.debug("free port " + p);
      add(p);
    }
  }
}
