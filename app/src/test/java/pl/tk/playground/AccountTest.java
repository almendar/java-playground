package pl.tk.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

record Invoice(int value) {
}

public class AccountTest {
    @Test
    void testFoldFunc() {
        var premiumUser = new Premium();
        var invoice = premiumUser.<Invoice>FoldFunc(
                p -> new Invoice(45),
                r -> new Invoice(29),
                f -> new Invoice(f.Users.size() * 15));
        assertEquals(new Invoice(45), invoice);
    }

    @Test
    void testFold() {
        var folder = new Account.Folder<Invoice>() {

            @Override
            public Invoice onPremium(Premium p) {
                return new Invoice(45);
            }

            @Override
            public Invoice onRegular(Regular r) {
                return new Invoice(29);
            }

            @Override
            public Invoice onFamilly(Fammily f) {
                return new Invoice(f.Users.size() * 15);
            }
        };

        var premiumUser = new Premium();
        var invoice = premiumUser.Fold(folder);
        assertEquals(new Invoice(45), invoice);
    }

}
