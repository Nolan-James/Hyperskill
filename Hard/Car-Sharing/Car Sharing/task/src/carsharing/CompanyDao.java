package carsharing;

import java.util.List;

public interface CompanyDao {

    public List<Company> getCompanies();
    public void createCompany(String companyName);
}
