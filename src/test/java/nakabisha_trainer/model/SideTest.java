package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SideTest {
    @Test
    void hasSenteAndGote() {
        assertEquals(Side.SENTE, Side.valueOf("SENTE"));
        assertEquals(Side.GOTE, Side.valueOf("GOTE"));
    }
}
