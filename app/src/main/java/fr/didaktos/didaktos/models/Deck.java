package fr.didaktos.didaktos.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Deck implements Parcelable {

    public static String PROPERTY_KEY = "property";
    public static String PROPERTIES_KEY = "properties";
    public static String PROPERTY_ID = "PROPERTY_ID";

    public static String AREA_KEY = "area";
    public static String LATITUDE_KEY = "latitude";
    public static String LONGITUDE_KEY = "longitude";
    public static String PRICE_KEY = "price";
    public static String SURFACE_KEY = "surface";
    public static String NB_ROOMS_KEY = "nbRooms";
    public static String DESCRIPTION_KEY = "description";
    public static String PHOTO_URL_KEY = "photoUrls";
    public static String PHOTO_DESCRIPTION_KEY = "photoDescriptions";
    public static String NB_PHOTOS_KEY = "nbPhotos";

    public static String IS_SOLD_KEY = "isSold";
    public static String DATE_CREATED_KEY = "dateCreated";
    public static String DATE_SOLD_KEY = "dateSold";
    public static String TYPE_KEY = "type";
    public static String AGENT_ID_KEY = "agentId";
    public static String POI_SCHOOL_KEY = "poiSchool";
    public static String POI_PARK_KEY = "poiPark";
    public static String POI_SHOPPING_KEY = "poiShopping";
    public static String POI_METRO_KEY = "poiMetro";

    public static String ADDRESS_STREET_NB_KEY = "streetNb";
    public static String ADDRESS_STREET_NAME_KEY = "streetName";
    public static String ADDRESS_APPT_NUMBER_KEY = "apptNb";
    public static String ADDRESS_ZIP_CODE_KEY = "zipCode";
    public static String ADDRESS_STATE_NB_KEY = "stateNb";
    public static String ADDRESS_CITY_KEY = "city";
    public static String ADDRESS_COUNTRY_KEY = "country";

    public static final String [] poisNames = {"School", "Park", "Shopping", "Metro"};
    public static final String [] typesNames = {"House", "Apartment", "Duplex", "Penthouse"};

    @PrimaryKey(autoGenerate = true) private long id;
    private String area;
    private Double latitude;
    private Double longitude;
    private long price;
    private int surface;

    private int nbRooms;
    private String description;
    private String photoUrl;
    private String photoDescription;
    private int nbPhotos;
    private int isSold;

    private int dateCreated;
    private int dateSold;
    private String type;
    private int agentID;

    private int poiSchool;
    private int poiPark;
    private int poiShopping;
    private int poiMetro;

    private String streetNb;
    private String streetName;
    private String apptNb;
    private String zipCode;
    private String stateNb;
    private String city;
    private String country;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Deck() { }

    public Deck(String area, Double latitude, Double longitude, long price, int surface,
                int nbRooms, String description, String photoUrl,
                String photoDescription, int nbPhotos, int isSold,
                int dateCreated, int dateSold, String type, int agentID,
                int poiSchool, int poiPark, int poiShopping, int poiMetro,
                String streetNb, String streetName, String apptNb, String zipCode,
                String stateNb, String city, String country) {
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.surface = surface;
        this.nbRooms = nbRooms;
        this.description = description;
        this.photoUrl = photoUrl;
        this.photoDescription = photoDescription;
        this.nbPhotos = nbPhotos;
        this.isSold = isSold;
        this.dateCreated = dateCreated;
        this.dateSold = dateSold;
        this.type = type;
        this.agentID = agentID;
        this.poiSchool = poiSchool;
        this.poiPark = poiPark;
        this.poiShopping = poiShopping;
        this.poiMetro = poiMetro;
        this.streetNb = streetNb;
        this.streetName = streetName;
        this.apptNb = apptNb;
        this.zipCode = zipCode;
        this.stateNb = stateNb;
        this.city = city;
        this.country = country;
    }


    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getArea() {
        return area;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public long getPrice() {
        return price;
    }
    public int getSurface() {
        return surface;
    }
    public int getNbRooms() {
        return nbRooms;
    }
    public String getDescription() {
        return description;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public String getPhotoDescription() {
        return photoDescription;
    }
    public int getNbPhotos() {
        return nbPhotos;
    }
    public int isSold() {
        return isSold;
    }
    public int getDateCreated() {
        return dateCreated;
    }
    public int getDateSold() {
        return dateSold;
    }
    public String getType() {
        return type;
    }
    public int getAgentID() {
        return agentID;
    }
    public int getPoiSchool() {
        return poiSchool;
    }
    public int getPoiPark() {
        return poiPark;
    }
    public int getPoiShopping() {
        return poiShopping;
    }
    public int getPoiMetro() {
        return poiMetro;
    }
    public String getStreetNb() {
        return streetNb;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getApptNb() {
        return apptNb;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getStateNb() {
        return stateNb;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }


    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public void setSurface(int surface) {
        this.surface = surface;
    }
    public void setNbRooms(int nbRooms) {
        this.nbRooms = nbRooms;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }
    public void setNbPhotos(int nbPhotos) {
        this.nbPhotos = nbPhotos;
    }
    public void setIsSold(int isSold) {
        this.isSold = isSold;
    }
    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateSold(int dateSold) {
        this.dateSold = dateSold;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
    public void setPoiSchool(int poiSchool) {
        this.poiSchool = poiSchool;
    }
    public void setPoiPark(int poiPark) {
        this.poiPark = poiPark;
    }
    public void setPoiShopping(int poiShopping) {
        this.poiShopping = poiShopping;
    }
    public void setPoiMetro(int poiMetro) {
        this.poiMetro = poiMetro;
    }
    public void setStreetNb(String streetNb) {
        this.streetNb = streetNb;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public void setApptNb(String apptNb) {
        this.apptNb = apptNb;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setStateNb(String stateNb) {
        this.stateNb = stateNb;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    //---------------------------
    //PARCELABLE METHODS
    //--------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(area);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeLong(price);
        out.writeInt(surface);
        out.writeInt(nbRooms);

        out.writeString(description);
        out.writeInt(nbPhotos);
        out.writeInt(isSold);
        out.writeInt(dateCreated);
        out.writeInt(dateSold);
        out.writeString(type);
        out.writeInt(agentID);

        out.writeInt(poiSchool);
        out.writeInt(poiPark);
        out.writeInt(poiShopping);
        out.writeInt(poiMetro);

        out.writeString(streetNb);
        out.writeString(streetName);
        out.writeString(apptNb);
        out.writeString(zipCode);
        out.writeString(stateNb);
        out.writeString(city);
        out.writeString(country);

        out.writeString(photoUrl);
        out.writeString(photoDescription);
    }

    public static final Creator<Deck> CREATOR
            = new Creator<Deck>() {

        public Deck createFromParcel(Parcel in) {
            return new Deck(in);
        }

        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };

    private Deck(Parcel in) {
        id = in.readLong();
        area = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        price = in.readLong();
        surface = in.readInt();
        nbRooms = in.readInt();

        description = in.readString();
        nbPhotos = in.readInt();
        isSold = in.readInt();
        dateCreated = in.readInt();
        dateSold = in.readInt();
        type = in.readString();
        agentID = in.readInt();

        poiSchool = in.readInt();
        poiPark = in.readInt();
        poiShopping = in.readInt();
        poiMetro = in.readInt();

        streetNb = in.readString();
        streetName = in.readString();
        apptNb = in.readString();
        zipCode = in.readString();
        stateNb = in.readString();
        city = in.readString();
        country = in.readString();

        photoUrl = in.readString();
        photoDescription = in.readString();
    }


    //---------------------------
    //CONTENT PROVIDER METHOD
    //--------------------------

    public static Deck fromContentValues(ContentValues values) {
        final Deck property = new Deck();
        if (values.containsKey(AREA_KEY)) property.setArea(values.getAsString(AREA_KEY));
        if (values.containsKey(LATITUDE_KEY)) property.setLatitude(values.getAsDouble(LATITUDE_KEY));
        if (values.containsKey(LONGITUDE_KEY)) property.setLongitude(values.getAsDouble(LONGITUDE_KEY));
        if (values.containsKey(PRICE_KEY)) property.setPrice(values.getAsLong(PRICE_KEY));
        if (values.containsKey(SURFACE_KEY)) property.setSurface(values.getAsInteger(SURFACE_KEY));
        if (values.containsKey(NB_ROOMS_KEY)) property.setNbRooms(values.getAsInteger(NB_ROOMS_KEY));
        if (values.containsKey(DESCRIPTION_KEY)) property.setDescription(values.getAsString(DESCRIPTION_KEY));
        if (values.containsKey(PHOTO_URL_KEY)) property.setPhotoUrl(values.getAsString(PHOTO_URL_KEY));
        if (values.containsKey(PHOTO_DESCRIPTION_KEY)) property.setPhotoDescription(values.getAsString(PHOTO_DESCRIPTION_KEY));
        if (values.containsKey(NB_PHOTOS_KEY)) property.setNbPhotos(values.getAsInteger(NB_PHOTOS_KEY));

        if (values.containsKey(IS_SOLD_KEY)) property.setIsSold(values.getAsInteger(IS_SOLD_KEY));
        if (values.containsKey(DATE_CREATED_KEY)) property.setDateCreated(values.getAsInteger(DATE_CREATED_KEY));
        if (values.containsKey(DATE_SOLD_KEY)) property.setDateSold(values.getAsInteger(DATE_SOLD_KEY));
        if (values.containsKey(TYPE_KEY)) property.setType(values.getAsString(TYPE_KEY));
        if (values.containsKey(AGENT_ID_KEY)) property.setAgentID(values.getAsInteger(AGENT_ID_KEY));

        if (values.containsKey(POI_SCHOOL_KEY)) property.setPoiSchool(values.getAsInteger(POI_SCHOOL_KEY));
        if (values.containsKey(POI_PARK_KEY)) property.setPoiPark(values.getAsInteger(POI_PARK_KEY));
        if (values.containsKey(POI_SHOPPING_KEY)) property.setPoiShopping(values.getAsInteger(POI_SHOPPING_KEY));
        if (values.containsKey(POI_METRO_KEY)) property.setPoiMetro(values.getAsInteger(POI_METRO_KEY));

        if (values.containsKey(ADDRESS_STREET_NB_KEY)) property.setStreetNb(values.getAsString(ADDRESS_STREET_NB_KEY));
        if (values.containsKey(ADDRESS_STREET_NAME_KEY)) property.setStreetName(values.getAsString(ADDRESS_STREET_NAME_KEY));
        if (values.containsKey(ADDRESS_APPT_NUMBER_KEY)) property.setApptNb(values.getAsString(ADDRESS_APPT_NUMBER_KEY));
        if (values.containsKey(ADDRESS_ZIP_CODE_KEY)) property.setZipCode(values.getAsString(ADDRESS_ZIP_CODE_KEY));
        if (values.containsKey(ADDRESS_STATE_NB_KEY)) property.setStateNb(values.getAsString(ADDRESS_STATE_NB_KEY));
        if (values.containsKey(ADDRESS_CITY_KEY)) property.setCity(values.getAsString(ADDRESS_CITY_KEY));
        if (values.containsKey(ADDRESS_COUNTRY_KEY)) property.setCountry(values.getAsString(ADDRESS_COUNTRY_KEY));

        return property;
    }


}
