package com.demo.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}
	
	private Long next = 1L;
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

	@Override
	public boolean updateCompany(Company company, Long id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if (optionalCompany.isPresent()) {
			Company companyUpdate = optionalCompany.get();
			companyUpdate.setDesc(company.getDesc());
			companyUpdate.setId(company.getId());
			companyUpdate.setName(company.getName());
			companyRepository.save(companyUpdate);
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public void createCompany(Company company) {
		// TODO Auto-generated method stub
		System.out.println("Company : "+company);
		company.setId(next++);
		System.out.println("next : "+company);
		companyRepository.save(company);
	}



}
