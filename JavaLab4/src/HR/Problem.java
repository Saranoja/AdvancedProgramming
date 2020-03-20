package HR;

import java.util.ArrayList;
import java.util.List;

public class Problem {

    private List<Hospital> hospitals;

    private  List<Resident> residents;

    public Problem(List<Hospital> hospitals, List<Resident> residents) {
        this.hospitals = hospitals;
        this.residents = residents;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }
}
