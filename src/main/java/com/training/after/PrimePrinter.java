package com.training.after;

public class PrimePrinter {
    public static final int NUMBER_OF_PRIMES = 1000;
    public static final int ROWS_PER_PAGE = 50;
    public static final int COLUMNS_PER_PAGE = 4;
    public static final String PAGE_HEADING_TEMPLATE = "The First "+ NUMBER_OF_PRIMES + " Prime Numbers === Page ";

    public static void main(String[] args) {

        PrimePrinter primePrinter = new PrimePrinter();
        int[] primesList = primePrinter.getNPrimeNumbersList();
        primePrinter.displayPrimes(primesList);

    }

    private int[] getNPrimeNumbersList() {

        int ORD = 2;
        boolean isPrime;
        int nthPrime = 1;
        int nextOddNumber = 1;
        final int ORDMAX = 30;
        int nextPrimeSquare = 9;
        int[] MULT = new int[ORDMAX+1];
        int[] primesList = new int[NUMBER_OF_PRIMES +1];
        primesList[1] = 2;

        while (nthPrime < NUMBER_OF_PRIMES) {
            do {
                nextOddNumber += 2;
                if( nextOddNumber == nextPrimeSquare) {
                    ORD++;
                    nextPrimeSquare = primesList[ORD] * primesList[ORD];
                    MULT[ORD-1]=nextOddNumber;
                }
                isPrime = isPrimeNumber(primesList, nextOddNumber, ORD, MULT);

            } while (!isPrime);
            nthPrime++;
            primesList[nthPrime] = nextOddNumber;
        }
        return primesList;
    }

    private void displayPrimes(int[] primesList) {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= NUMBER_OF_PRIMES) {
            printPage(primesList, pageNumber, pageOffset);
            pageNumber++;
            pageOffset += (ROWS_PER_PAGE * COLUMNS_PER_PAGE);
        }
    }

    private void printPage(int[] primesList, int pageNumber, int pageOffset) {
        printPageHeading(pageNumber);
        printPageContent(primesList, pageOffset);
    }

    private void printPageContent(int[] primesList, int pageOffset) {
        for (int rowOffset = pageOffset; rowOffset <= (pageOffset + ROWS_PER_PAGE -1); rowOffset++) {
            for (int columnIndex = 0; columnIndex <= COLUMNS_PER_PAGE - 1; columnIndex++)
                if (rowOffset + columnIndex * ROWS_PER_PAGE <= NUMBER_OF_PRIMES)
                    System.out.printf("%10d", primesList[rowOffset + columnIndex * ROWS_PER_PAGE]);
            System.out.println();
        }
        System.out.println("\f");
    }

    private void printPageHeading(int pageNumber) {
        System.out.println(PAGE_HEADING_TEMPLATE + pageNumber + "\n");
    }

    private boolean isPrimeNumber(int[] primesList, int nextOddNumber, int ORD, int[] MULT) {
        boolean isPrime = true;
        int N = 2;
        while (N< ORD && isPrime) {
            while (MULT[N]< nextOddNumber) {
                //Odd+Odd = even and Odd+Odd+Odd = Odd. Gives next odd number which is not prime
                MULT[N] += primesList[N] + primesList[N];
            }
            if (MULT[N] == nextOddNumber) {
                isPrime = false;
            }
            N++;
        }
        return isPrime;
    }
}
