import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

// this class just holds the data for one journey — it doesn't do any logic
// JourneyManager is the one that creates and manages these
public class Journey {

    // every field is private so nothing outside can change them directly
    private int id;
    private LocalDate date;
    private int fromZone;
    private int toZone;
    private int zonesCrossed;// calculated in the constructor, not passed in
    private CityRideDataset.PassengerType passengerType;
    private CityRideDataset.TimeBand timeBand;
    private BigDecimal baseFare;
    private BigDecimal discount;
    private BigDecimal fareCharged;

    public Journey(int id,LocalDate date, int fromZone, int toZone,
                   CityRideDataset.PassengerType passengerType,
                   CityRideDataset.TimeBand timeBand,
                   BigDecimal baseFare, BigDecimal discount, BigDecimal fareCharged) {
        this.id            = id;
        this.date          = date;
        this.fromZone      = fromZone;
        this.toZone        = toZone;
        // working out zones crossed here so I don't have to pass it in every time
        this.zonesCrossed  = Math.abs(toZone - fromZone) + 1;
        this.passengerType = passengerType;
        this.timeBand      = timeBand;
        this.baseFare      = baseFare;
        this.discount      = discount;
        this.fareCharged   = fareCharged;
    }

    // getters — the only way to read the data from outside this class
    public int getId() {
        return id; }
    public LocalDate getDate() {
        return date;}
    public int getFromZone() {
        return fromZone; }
    public int getToZone() {
        return toZone; }
    public int getZonesCrossed() {
        return zonesCrossed; }
    public CityRideDataset.PassengerType getPassengerType() {
        return passengerType; }
    public CityRideDataset.TimeBand getTimeBand() {
        return timeBand; }
    public BigDecimal getBaseFare() {
        return baseFare; }
    public BigDecimal getDiscount() {
        return discount; }
    public BigDecimal getFareCharged() {
        return fareCharged; }

    public void setFromZone ( int fromZone){
        this.fromZone = fromZone;
        this.zonesCrossed = Math.abs(this.toZone - fromZone) + 1;
    }

    public void setToZone(int toZone){
        this.toZone = toZone;
        this.zonesCrossed = Math.abs(toZone - this.fromZone) + 1;
    }

    public void setPassengerType(CityRideDataset.PassengerType passengerType){
        this.passengerType = passengerType;
    }

    public void setTimeBand(CityRideDataset.TimeBand timeBand){
        this.timeBand = timeBand;
    }

    public void setDate (LocalDate date){
        this.date = date;
    }

    public void setBaseFare (BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public void setDiscount ( BigDecimal discount){
        this.discount = discount;
    }

    public void setFareCharged(BigDecimal fareCharged){
        this.fareCharged = fareCharged;
    }



    // this is what gets printed whenever a Journey object is used in a println
    // RoundingMode.HALF_UP makes sure the pence don't go weird (e.g. 2.5000001)
    @Override
    public String toString() {
        return "ID:" + id
                + "  Date: " + date
                + "  From:" + fromZone
                + "  To:" + toZone
                + "  Zones:" + zonesCrossed
                + "  Type:" + passengerType
                + "  Band:" + timeBand
                + "  Base:£" + baseFare.setScale(2, RoundingMode.HALF_UP)
                + "  Discount:£" + discount.setScale(2, RoundingMode.HALF_UP)
                + "  Charged:£" + fareCharged.setScale(2, RoundingMode.HALF_UP);
    }
}