/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.Chart;

public class ChartRepository extends AbstractRepository<Chart> {

    public ChartRepository() {
        super();
    }

    public Chart findById(int id) {
        return entityManager.find(Chart.class, id);
    }

}
