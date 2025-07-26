public class FinancialForecast 
{
    public static double futureValue(double initialValue, double rate, int years) 
    {
        if (years == 0) 
        {
            return initialValue;
        }
        return futureValue(initialValue, rate, years - 1) * (1 + rate);
    }

    public static void main(String[] args) 
    {
        double initialInvestment = 1000.0;  
        double annualGrowthRate = 0.05;     
        int forecastYears = 5;              
        double futureVal = futureValue(initialInvestment, annualGrowthRate, forecastYears);

        System.out.println("Future Value after " + forecastYears + " years: $" + String.format("%.2f", futureVal));
    }
}


