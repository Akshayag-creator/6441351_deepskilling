public class DependencyInjectionExample 
{
    interface CustomerRepository 
    {
        String findCustomerById(String customerId);
    }

    static class CustomerRepositoryImpl implements CustomerRepository 
    {
        @Override
        public String findCustomerById(String customerId) 
        {
            return "Customer[ID=" + customerId + ", Name=Alice, Email=alice@example.com]";
        }
    }

    static class CustomerService 
    {
        private CustomerRepository customerRepository;

        public CustomerService(CustomerRepository customerRepository) 
        {
            this.customerRepository = customerRepository;
        }

        public void displayCustomer(String customerId) 
        {
            String customer = customerRepository.findCustomerById(customerId);
            System.out.println("CustomerService: Found customer => " + customer);
        }
    }

    public static void main(String[] args) 
    {
        CustomerRepository repository = new CustomerRepositoryImpl();

        CustomerService service = new CustomerService(repository);

        System.out.println("== Fetching Customer Details ==");
        service.displayCustomer("C101");
    }
}


