package apps.perry.mathslearninggame.backend;

/**
 * Manage the game score
 */
public class ScoreManager {

    // Score value (setting it as a 64-bit int to prevent hilarious overflow insanity)
    private long m_score;

    // Singleton instance of this ScoreManager
    private static ScoreManager m_instance;

    private ScoreManager() {
        m_score = 0;
    }

    /**
     * Get the singleton instance of this ScoreManager
     * @return
     */
    public static ScoreManager getInstance() {
        if (m_instance == null) {
            m_instance = new ScoreManager();
        }
        return m_instance;
    }

    /**
     * Get the game score
     * @return Score
     */
    public long score() {
       return m_score;
    }

    /**
     * Add val to the score
     * @param val The amount to add to the score
     */
    public void addScore(long val) {
        if (val >= 0) m_score += val;
    }

    /**
     * Subtract val from the score
     * @param val The amount to subtract from the score
     */
    public void subtractScore(long val) {
        if (val >= 0) m_score -= val;
    }

    /**
     * Reset the score value to 0
     */
    public void resetScore() {
        m_score = 0;
    }
}
