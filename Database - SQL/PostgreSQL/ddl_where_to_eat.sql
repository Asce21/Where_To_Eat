-- Drop Tables
DROP TABLE IF EXISTS SoloRestaurantOperations;
DROP TABLE IF EXISTS ChainRestaurantOperations;
DROP TABLE IF EXISTS Hours;
DROP TABLE IF EXISTS Days;
DROP TABLE IF EXISTS SoloRestaurantMeals;
DROP TABLE IF EXISTS ChainRestaurantMeals;
DROP TABLE IF EXISTS Meals;
DROP TABLE IF EXISTS SoloRestaurants;
DROP TABLE IF EXISTS LocationUnits;
DROP TABLE IF EXISTS ChainRestaurantLocations;
DROP TABLE IF EXISTS Locations;
DROP TABLE IF EXISTS AddressSuffixes;
DROP TABLE IF EXISTS AddressDirections;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS ZipLocations;
DROP TABLE IF EXISTS ChainRestaurants;
DROP TABLE IF EXISTS LocationSeparators;
DROP TABLE IF EXISTS LocationTypes;
DROP TABLE IF EXISTS Countries;
DROP TABLE IF EXISTS States;
DROP TABLE IF EXISTS Cities;
DROP TABLE IF EXISTS SurfaceRoadSuffixes;
DROP TABLE IF EXISTS SurfaceRoadNames;
DROP TABLE IF EXISTS SurfaceRoadDirections;
DROP TABLE IF EXISTS SurfaceRoadNumbers;

-- Drop Enums
DROP TYPE IF EXISTS STATE_CODE;
DROP TYPE IF EXISTS STATE_NAME;

-- Create Enums
CREATE Type STATE_NAME AS ENUM('Alabama', 'Alaska', 'American Samoa', 'Arizona', 'Arkansas', 'California', 'Colorado',
                               'Connecticut', 'Delaware', 'District of Columbia', 'Florida', 'Georgia', 'Guam', 'Hawaii',
                               'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
                               'Maryland', 'Marshall Islands', 'Massachusetts', 'Michigan', 'Micronesia', 'Minnesota ',
                               'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey',
                               'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Northern Mariana', 'Ohio',
                               'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island', 'South Carolina',
                               'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Virgin Islands', 'Washington',
                               'West Virginia', 'Wisconsin', 'Wyoming');
CREATE Type STATE_CODE AS ENUM('AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FL', 'FM', 'GA', 'GU', 'HI',
                               'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MH', 'MI', 'MN', 'MS', 'MO', 'MP',
                               'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY', 'OH', 'OK', 'OR', 'PA', 'PR',
                               'PW', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VI', 'VT', 'WA', 'WI', 'WV', 'WY');

-- Create Tables
CREATE TABLE SurfaceRoadNumbers (
	street_number   INTEGER             NOT NULL,
	CONSTRAINT PK_surface_road_numbers  PRIMARY KEY(street_number)
);-- End of the create table statement for tha table SurfaceRoadNumbers

CREATE TABLE SurfaceRoadDirections (
    abbreviation        VARCHAR(2)               NOT NULL,
    street_heading      VARCHAR(11)              NOT NULL,
    CONSTRAINT PK_surface_road_directions        PRIMARY KEY(abbreviation),
    CONSTRAINT UK_02_surface_road_directions    UNIQUE(street_heading)
);-- End of the create table statement for tha table SurfaceRoadDirections

CREATE TABLE SurfaceRoadNames (
	street_name     VARCHAR(30)	        NOT NULL,
	CONSTRAINT PK_surface_road_names    PRIMARY KEY(street_name)
);-- End of the create table statement for tha table SurfaceRoadNames

CREATE TABLE SurfaceRoadSuffixes (
	street_suffix       VARCHAR(5)	       NOT NULL,
	street_suffix_long  VARCHAR(15)        NOT NULL,
	CONSTRAINT PK_surface_road_suffixes    PRIMARY KEY(street_suffix)
);-- End of the create table statement for tha table SurfaceRoadSuffixes

CREATE TABLE Cities (
	city_name     VARCHAR(30)	        NOT NULL,
	CONSTRAINT PK_cities                PRIMARY KEY(city_name)
);-- End of the create table statement for tha table Cities

CREATE TABLE States (
	state_code     STATE_CODE	        NOT NULL,
	state_name     STATE_NAME	        NOT NULL,
	CONSTRAINT PK_states                PRIMARY KEY(state_code),
	CONSTRAINT UK_02_states             UNIQUE(state_name)
);-- End of the create table statement for tha table States

CREATE TABLE Countries (
	country_code     VARCHAR(4)	        NOT NULL,
	-- This is the ISO code;Reference: https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
	country_name     VARCHAR(32)	    NOT NULL,
	CONSTRAINT PK_countries             PRIMARY KEY(country_code),
	CONSTRAINT UK_02_countries          UNIQUE(country_name)
);-- End of the create table statement for tha table Countries

CREATE TABLE ChainRestaurants (
	chain_website     VARCHAR(50)	        NOT NULL,
	chain_name        VARCHAR(50)	        NOT NULL,
	CONSTRAINT PK_chain_restaurants         PRIMARY KEY(chain_name),
	CONSTRAINT UK_02_chain_restaurants      UNIQUE(chain_website)
);-- End of the create table statement for tha table ChainRestaurants

CREATE TABLE LocationTypes  (
    type            VARCHAR(8)      NOT NULL,
    type_long       VARCHAR(16)     NOT NULL,
    CONSTRAINT PK_location_types    PRIMARY KEY(type)
);-- End of the create table statement for tha table LocationTypes

CREATE TABLE LocationSeparators (
    separator            VARCHAR(4)         NOT NULL,
    CONSTRAINT PK_location_separators    PRIMARY KEY(separator)
);-- End of the create table statement for tha table LocationSeparators

CREATE TABLE ZipLocations (
	zip_code      VARCHAR(5)   	                NOT NULL,
	city_name     VARCHAR(30)	                NOT NULL,
	state_code     STATE_CODE	                NOT NULL,
	country_code   VARCHAR(4)	                NOT NULL,
	CONSTRAINT PK_fffff                         PRIMARY KEY(zip_code),
	CONSTRAINT FK_01_zip_locations_cities       FOREIGN KEY(city_name)      REFERENCES Cities(city_name),
	CONSTRAINT FK_02_zip_locations_states       FOREIGN KEY(state_code)     REFERENCES States(state_code),
	CONSTRAINT FK_03_zip_locations_countries    FOREIGN KEY(country_code)   REFERENCES Countries(country_code)
);-- End of the create table statement for tha table ZipLocations

CREATE TABLE Addresses (
	address_id              SERIAL	                    NOT NULL,
	street_number           INTEGER	                    NOT NULL,
	street_name             VARCHAR(30)	                NOT NULL,
	zip_code                VARCHAR(5)   	            NOT NULL,
	CONSTRAINT PK_addresses                             PRIMARY KEY(address_id),
	CONSTRAINT FK_01_addresses_surface_road_numbers     FOREIGN KEY(street_number)
	    REFERENCES SurfaceRoadNumbers(street_number),
	CONSTRAINT FK_02_addresses_surface_road_names       FOREIGN KEY(street_name)
	    REFERENCES SurfaceRoadNames(street_name),
	CONSTRAINT FK_03_addresses_zip_locations            FOREIGN KEY(zip_code)
	    REFERENCES ZipLocations(zip_code)
);-- End of the create table statement for tha table Addresses

CREATE TABLE AddressDirections (
	address_id              INTEGER	                             NOT NULL,
	street_direction        VARCHAR(2)	                         NOT NULL,
	CONSTRAINT PK_address_directions                             PRIMARY KEY(address_id),
	CONSTRAINT FK_01_address_directions_addresses                FOREIGN KEY(address_id)
	    REFERENCES Addresses(address_id),
	CONSTRAINT FK_02_address_directions_surface_road_directions  FOREIGN KEY(street_direction)
	    REFERENCES SurfaceRoadDirections(abbreviation)
);-- End of the create table statement for tha table AddressDirections

CREATE TABLE AddressSuffixes (
	address_id              INTEGER	                    NOT NULL,
	street_suffix           VARCHAR(5)                  NOT NULL,
	CONSTRAINT PK_address_suffixes                      PRIMARY KEY(address_id, street_suffix),
	CONSTRAINT FK_01_address_suffixes_addresses         FOREIGN KEY(address_id)
	    REFERENCES Addresses(address_id),
	CONSTRAINT FK_02_addresses_surface_road_suffixes    FOREIGN KEY(street_suffix)
	    REFERENCES SurfaceRoadSuffixes(street_suffix)
);-- End of the create table statement for tha table AddressSuffixes

CREATE TABLE Locations (
	location_type       VARCHAR(5)	                NOT NULL,
	location_space      VARCHAR(4)                  NOT NULL,
	CONSTRAINT PK_locations                         PRIMARY KEY(location_type, location_space),
	CONSTRAINT FK_01_locations_location_types       FOREIGN KEY(location_type)
	    REFERENCES LocationTypes(type),
	CONSTRAINT FK_02_locations_location_separators  FOREIGN KEY(location_space)
	    REFERENCES LocationSeparators(separator)
);-- End of the create table statement for tha table Locations

CREATE TABLE ChainRestaurantLocations (
	restaurant_id          SERIAL	                                NOT NULL,
	chain_name             VARCHAR(30)	                            NOT NULL,
	restaurant_phone       VARCHAR(12)	                            NOT NULL,
	address_id             INTEGER	                                NOT NULL,
	CONSTRAINT PK_chain_restaurant_locations                        PRIMARY KEY(restaurant_id),
	CONSTRAINT UK_02_chain_restaurant_locations                     UNIQUE(restaurant_phone),
	CONSTRAINT FK_01_chain_restaurant_locations_chain_restaurants   FOREIGN KEY(chain_name)
	    REFERENCES ChainRestaurants(chain_name),
	CONSTRAINT FK_02_chain_restaurant_locations_addresses           FOREIGN KEY(address_id)
	    REFERENCES Addresses(address_id)
);-- End of the create table statement for tha table ChainRestaurantLocations

CREATE TABLE LocationUnits (
	location_type           VARCHAR(5)	                        NOT NULL,
	location_space          VARCHAR(15)                         NOT NULL,
	restaurant_id           INTEGER	                            NOT NULL,
	CONSTRAINT PK_location_units                                PRIMARY KEY(location_type, location_space, restaurant_id),
	CONSTRAINT FK_0_location_units_locations                    FOREIGN KEY(location_type, location_space)
	    REFERENCES Locations(location_type, location_space),
    CONSTRAINT FK_02_location_units_chain_restaurant_locations  FOREIGN KEY(restaurant_id)
        REFERENCES chainrestaurantlocations(restaurant_id)
);-- End of the create table statement for tha table LocationUnits

CREATE TABLE SoloRestaurants (
	restaurant_name        VARCHAR(50)	                            NOT NULL,
	restaurant_phone       VARCHAR(12)	                            NOT NULL,
	restaurant_website     VARCHAR(50)	                            NOT NULL,
	address_id             INTEGER	                                NOT NULL,
	CONSTRAINT PK_solo_restaurants                                  PRIMARY KEY(restaurant_phone),
	CONSTRAINT FK_01_solo_restaurants_addresses                     FOREIGN KEY(address_id)
	    REFERENCES Addresses(address_id)
);-- End of the create table statement for tha table SoloRestaurants

CREATE TABLE Meals (
	meal_name              VARCHAR(15)	                            NOT NULL,
	CONSTRAINT PK_meals    PRIMARY KEY(meal_name)
);-- End of the create table statement for tha table Meals

CREATE TABLE ChainRestaurantMeals (
	restaurant_id          INTEGER	                                    NOT NULL,
	meal_name              VARCHAR(15)	                                NOT NULL,
	CONSTRAINT PK_cain_restaurant_meals                                 PRIMARY KEY( restaurant_id, meal_name),
	CONSTRAINT FK_01_cain_restaurant_meals_cain_restaurant_locations    FOREIGN KEY(restaurant_id)
	    REFERENCES ChainRestaurantLocations(restaurant_id),
	CONSTRAINT FK_02_cain_restaurant_meals_meals                        FOREIGN KEY(meal_name)
	    REFERENCES Meals(meal_name)
);-- End of the create table statement for tha table ChainRestaurantMeals

CREATE TABLE SoloRestaurantMeals (
	meal_name              VARCHAR(15)	                        NOT NULL,
	restaurant_phone       VARCHAR(12)	                        NOT NULL,
	CONSTRAINT PK_solo_restaurant_meals                         PRIMARY KEY(meal_name, restaurant_phone),
	CONSTRAINT FK_01_solo_restaurant_meals_meals                FOREIGN KEY(meal_name)                      REFERENCES Meals(meal_name),
	CONSTRAINT FK_02_solo_restaurant_meals__solo_restaurants    FOREIGN KEY(restaurant_phone)               REFERENCES SoloRestaurants(restaurant_phone)
);-- End of the create table statement for tha table SoloRestaurantMeals

CREATE TABLE Days (
	day_abbreviation     VARCHAR(3)	        NOT NULL,
	day_name             VARCHAR(10)	        NOT NULL,
	CONSTRAINT PK_days                      PRIMARY KEY(day_abbreviation),
	CONSTRAINT UK_02_days                   UNIQUE(day_name)
);-- End of the create table statement for tha table Days

CREATE TABLE Hours (
	time        TIME	        NOT NULL,
	CONSTRAINT PK_hours         PRIMARY KEY(time)
);-- End of the create table statement for tha table Hours

CREATE TABLE ChainRestaurantOperations (
	restaurant_id          INTEGER	                                                    NOT NULL,
	day_abbreviation       VARCHAR(3)	                                                NOT NULL,
	meal_name              VARCHAR(15)	                                                NOT NULL,
	opening_time           TIME	                                                        NOT NULL,
	closing_time           TIME	                                                        NOT NULL,
	CONSTRAINT PK_chain_restaurant_operations                                           PRIMARY KEY(day_abbreviation, restaurant_id, meal_name, opening_time, closing_time),
	CONSTRAINT FK_01_chain_restaurant_operations_days                                   FOREIGN KEY(day_abbreviation)
	    REFERENCES Days(day_abbreviation),
	CONSTRAINT FK_02_chain_restaurant_operations_chain_restaurant_meals                 FOREIGN KEY(restaurant_id, meal_name)
	    REFERENCES ChainRestaurantMeals(restaurant_id, meal_name),
	CONSTRAINT FK_03_chain_restaurant_operations_hours                                  FOREIGN KEY(opening_time)
	    REFERENCES Hours(time),
	CONSTRAINT FK_04_chain_restaurant_operations_hours                                  FOREIGN KEY(closing_time)
	    REFERENCES Hours(time)-- Creating multiple references to the table Hours was done to visually match the Relation Scheme
);-- End of the create table statement for tha table ChainRestaurantOperations

CREATE TABLE SoloRestaurantOperations (
	restaurant_phone       VARCHAR(12)	                                    NOT NULL,
	day_abbreviation       VARCHAR(3)	                                    NOT NULL,
	meal_name              VARCHAR(15)	                                    NOT NULL,
	opening_time           TIME	                                            NOT NULL,
	closing_time           TIME	                                            NOT NULL,
	CONSTRAINT PK_solo_restaurant_operations                                PRIMARY KEY(day_abbreviation, meal_name, restaurant_phone, opening_time, closing_time),
	CONSTRAINT FK_01_solo_restaurant_operations_days                        FOREIGN KEY(day_abbreviation)
	    REFERENCES Days(day_abbreviation),
	CONSTRAINT FK_02_solo_restaurant_operations_solo_restaurant_meals       FOREIGN KEY(meal_name, restaurant_phone)
	    REFERENCES SoloRestaurantMeals(meal_name, restaurant_phone),
	CONSTRAINT FK_03_solo_restaurant_operations_time                    FOREIGN KEY(opening_time)
        REFERENCES Hours(time),
    CONSTRAINT FK_04_solo_restaurant_operations_hours                   FOREIGN KEY(closing_time)
        REFERENCES Hours(time)-- Creating multiple references to the table Hours was done to visually match the Relation Scheme
);-- End of the create table statement for tha table SoloRestaurantOperations