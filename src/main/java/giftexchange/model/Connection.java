package giftexchange.model;

public class Connection {
    private String from;
    private String to;
    private String arrows;

    public Connection(String from, String to) {
        this.from = from;
        this.to = to;
        this.arrows = "to";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getArrows() {
        return arrows;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
