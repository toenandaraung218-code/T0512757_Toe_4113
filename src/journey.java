import java.math.BigDecimal;
public class journey {

    public int id;
    public int fromZone;
    public int toZone;
    public int zonesCrossed;
    public CityRideDataset.PassengerType passengerType;
    public CityRideDataset.TimeBand timeBand;
    public BigDecimal baseFare;
    public BigDecimal discount;
    public BigDecimal fareCharged;

    public journey(int id, int fromZone, int toZone,
                   CityRideDataset.PassengerType passengerType,
                   CityRideDataset.TimeBand timeBand,
                   BigDecimal baseFare, BigDecimal discount, BigDecimal fareCharged) {
        this.id            = id;
        this.fromZone      = fromZone;
        this.toZone        = toZone;
        this.zonesCrossed  = Math.abs(toZone - fromZone) + 1;
        this.passengerType = passengerType;
        this.timeBand      = timeBand;
        this.baseFare      = baseFare;
        this.discount      = discount;
        this.fareCharged   = fareCharged;
    }

    public String toString() {
        return "ID:" + id
                + "  From:" + fromZone
                + "  To:" + toZone
                + "  Zones:" + zonesCrossed
                + "  Type:" + passengerType
                + "  Band:" + timeBand
                + "  Base:£" + baseFare
                + "  Discount:£" + discount
                + "  Charged:£" + fareCharged;
    }
}