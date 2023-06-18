
public class WaitLineDriver {
    public static void main(String[] args) throws EmptyQueueException {
        WaitLine waitLine = new WaitLine();
        
        // Explanation
        System.out.println("** WaitLine simulation **");
        System.out.println("\n* When a client enters a line, they are assigned a random transaction time.");
        System.out.println("  The transaction time is the time needed to wait for an order to finish.");
        System.out.println("* While we wait for this transaction to be completed, another client can enter");
        System.out.println("  the line and begin their order. They will be given a transaction time.");
        System.out.println("* Once the current transaction ends, the next client's transaction can begin.");
        System.out.println("* The time interval between entering the line and starting your service (transaction)");
        System.out.println("  is the time waited.");
        System.out.println("* Details are shown below the customer queue.\n\n");
        
        waitLine.simulate(20, 0.5, 5);
        waitLine.displayResults();
    }
}
