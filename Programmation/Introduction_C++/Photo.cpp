
#include "Photo.h"


void Photo::show(std::ostream & s) const {
    Multimedia::show(s);
    s << "Latitude : " << latitude << std::endl;
    s  << "Longitude : " << longitude << std::endl;
}

void Photo::play() const {
  std::string arg("imagej " + getPath()  + " &");
  system(arg.data());
}
