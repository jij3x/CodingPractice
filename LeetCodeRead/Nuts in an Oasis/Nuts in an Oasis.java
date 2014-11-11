public class Solution {
    double getMaxNuts(double N, double D, double C, double F) {
        if (N <= C)
            return Math.max(0, N - D * F);

        int numTrips = (int) (2 * (Math.ceil(N / C) - 1) + 1);
        double costPerKm = numTrips * F;
        double remainingNuts = C * (Math.ceil(N / C) - 1);
        double traveled = (N - remainingNuts) / costPerKm;

        if (traveled >= D)
            return N - D * costPerKm;

        return getMaxNuts(remainingNuts, D - traveled, C, F);
    }
}
