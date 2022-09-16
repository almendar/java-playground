package pl.tk.playground;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;



public sealed class Account permits Premium, Regular, Fammily {


    public <R> R FoldFunc(
            Function<Premium, R> onPremium,
            Function<Regular, R> onRegular,
            Function<Fammily, R> onFamilly) {

                switch (this.getClass()) {
                    case value:
                        
                        break;
                
                    default:
                        break;
                }



        if (this instanceof Premium) {
            return onPremium.apply((Premium) this);
        } else if (this instanceof Regular) {
            return onRegular.apply((Regular) this);
        } else if (this instanceof Regular) {
            return onFamilly.apply((Fammily) this);
        } else {
            throw new IllegalStateException("Well what did you pass here: " + this.getClass().toString() + ". Ehh.");
        }
    }


    interface Folder<R> {
        R onPremium(Premium p);

        R onRegular(Regular r);

        R onFamilly(Fammily f);
    }



    public <R> R Fold(
            Folder<R> folder) {
        if (this instanceof Premium) {
            return folder.onPremium((Premium) this);
        } else if (this instanceof Regular) {
            return folder.onRegular((Regular) this);
        } else if (this instanceof Regular) {
            return folder.onFamilly((Fammily) this);
        } else {
            throw new IllegalStateException("Well what did you pass here: " + this.getClass().toString() + ". Ehh.");
        }
    }
}

final class Premium extends Account {
}

final class Regular extends Account {
}

final class Fammily extends Account {
    final public Collection<Account> Users;

    public Fammily(Collection<Account> users) {
        var acc = new LinkedList<Account>();
        this.Users = acc;
        for (Account account : users) {
            acc.add(account);
        }
    }
}
