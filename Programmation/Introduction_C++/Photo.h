#ifndef Graph_Photo
#define Graph_Photo

#include <iostream>
#include "Multimedia.h"


class Photo : public Multimedia {

private:
    double longitude{};
    double latitude{};

public:
    Photo(){}
    Photo(std::string const& name, std::string const& path, double longitude, double latitude) :
    Multimedia(name, path), longitude(longitude), latitude(latitude){}

    ~Photo(){}

    void setLongitude(double longitude) {this->longitude = longitude;}
    void setLatitude(double latitude) {this->latitude = latitude;}
    const double getLongitude() const {return longitude;}
    const double getLatitude() const {return latitude;}

    void show(std::ostream & s) const final {
    Multimedia::show(s);
    s << "Latitude : " << latitude << std::endl;
    s  << "Longitude : " << longitude << std::endl;
    }

    void play() const final {
      std::string arg("imagej " + getPath()  + " &");
      system(arg.data());
    }


};

#endif
