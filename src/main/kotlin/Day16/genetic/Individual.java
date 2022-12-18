package Day16.genetic;


import Day16.Path;

public class Individual {
    public PathIndividual pi = new PathIndividual( new Path());

    private int fitness = 0;

    // Create a random individual
    public void generateIndividual() {
        pi.fillRandom();


    }



    /* Public methods */
    public int size() {
        return pi.size();
    }

    public int getFitness() {
        if (fitness == 0) {
           // fitness = FitnessCalc.getFitness(this);
            fitness = pi.getPath().calcPressure();
        }
        return fitness;
    }

    @Override
    public String toString() {

        return pi.getPath().toString();
    }
}