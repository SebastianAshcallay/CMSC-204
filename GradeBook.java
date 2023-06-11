import java.util.ArrayList;

public class GradeBook
{
   private double[] scores;
   private int scoresSize;

   /**
      Constructs a gradebook with no scores and a given capacity.
      @capacity the maximum number of scores in this gradebook
   */
   public GradeBook(int capacity)
   {
      scores = new double[capacity];
      scoresSize = 0;
   }

   /**
      Adds a score to this gradebook.
      @param score the score to add
      @return true if the score was added, false if the gradebook is full
   */
   public boolean addScore(double score)
   {
      if (scoresSize < scores.length)
      {
         scores[scoresSize] = score;
         scoresSize++;
         return true;
      }
      else
         return false;      
   }

   /**
      Computes the sum of the scores in this gradebook.
      @return the sum of the scores
   */
   public double sum()
   {
      double total = 0;
      for (int i = 0; i < scoresSize; i++)
      {
         total = total + scores[i];
      }
      return total;
   }
      
   /**
      Gets the minimum score in this gradebook.
      @return the minimum score, or 0 if there are no scores.
   */
   public double minimum()
   {
      
      double smallest = -99;
      
	  // STUDENT TODO: Implement your logic here
      if (this.scores.length == 0) {
    	  return 0;
      
      } else {
    	  
    	  smallest = scores[0];
    	  
    	  for (int i = 0; i < getScoreSize(); i++) {
    		  if (this.scores[i] < smallest)
    			  smallest = this.scores[i];
    	  }
      }
	  
      return smallest;
   }

   /**
      Gets the final score for this gradebook.
      @return the sum of the scores, with the lowest score dropped if 
      there are at least two scores, or 0 if there are no scores.
   */
   public double finalScore() 
   {
      if (scoresSize == 0)
         return 0;
      else if (scoresSize == 1)
         return scores[0];
      else
         return sum() - minimum();
   }
   
   /**
    * Getter Method: getScoreSize()
    * @return scoreSize
    */
   public int getScoreSize() {
	   return this.scoresSize;
   }
   
   @Override
   public String toString() {
	   
	   /*
	    * This toString() method returns all scores
	    * that are in the scores array, taking into account
	    * the scoresSize variable.
	    */
	   
	   if (this.scores.length == 0) {
		   return "";
		   
	   } else {
		   String scoreStr = "";
		   for (int i = 0; i < getScoreSize(); i++) {
			   scoreStr += this.scores[i];
			   if (i < getScoreSize() - 1)
				   scoreStr += " ";
		   }
	   
		   return scoreStr;
	   }
	   
   }
   
   
}

