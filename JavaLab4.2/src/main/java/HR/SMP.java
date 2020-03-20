/**
 * @author : Calin Irina, I2E2
 */
package HR;

import java.util.List;
import java.util.Map;

public class SMP {
    private List<Element> elementList;
    private List<Partition> partitionList;
    private Map<Element, List<Partition>> elementPrefMap;
    private Map<Partition, List<Element>> partPrefMap;
}
