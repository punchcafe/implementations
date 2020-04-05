package dev.punchcafe.implementations.abstrakt.factory.key;

public class AuthToken implements Key {
    @Override
    public boolean unlock() {
        return true;
    }
}
