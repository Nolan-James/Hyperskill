package carsharing;

import java.util.List;

public interface CompanyDao {

    public List<Company> getCompanies();
    public Company getCompany(int id);
    public void createCompany(String companyName);
}
