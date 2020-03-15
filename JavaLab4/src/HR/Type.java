//Author: Calin Irina, I2E2
package HR;
public interface Type extends Comparable {

    String getName();

    public default int compareTo(Object o) {
        if(o instanceof Type){
            return this.getName().compareTo(((Type) o).getName());
        }
        return 0;
    }

}