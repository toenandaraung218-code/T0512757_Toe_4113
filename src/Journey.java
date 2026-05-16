import java.math.BigDecimal;
public class Journey {

    private int id;
    private int fromZone;
    private int toZone;
    private int zonesCrossed;
    private CityRideDataset.PassengerType passengerType;
    private CityRideDataset.TimeBand timeBand;
    private BigDecimal baseFare;
    private BigDecimal discount;
    private BigDecimal fareCharged;

    public Journey(int id, int fromZone, int toZone,
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
    public int getId() { return id; }
    public int getFromZone() { return fromZone; }
    public int getToZone() { return toZone; }
    public int getZonesCrossed() { return zonesCrossed; }
    public CityRideDataset.PassengerType getPassengerType() { return passengerType; }
    public CityRideDataset.TimeBand getTimeBand() { return timeBand; }
    public BigDecimal getBaseFare() { return baseFare; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getFareCharged() { return fareCharged; }


    public String toString() {
        return "ID:" + id
                + "  From:" + fromZone
                + "  To:" + toZone
                + "  Zones:" + zonesCrossed
                + "  Type:" + passengerType
                + "  Band:" + timeBand
                + "  Base:£" + baseFare.setScale(2)
                + "  Discount:£" + discount.setScale(2)
                + "  Charged:£" + fareCharged.setScale(2);
    }
}