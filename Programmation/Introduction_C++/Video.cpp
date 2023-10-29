
#include "Video.h"

/*
Video::Video(std::string name, std::string path, unsigned int duree) {
  setName(name);
  setPath(path);
  this->duree = duree;
} */

void Video::show(std::ostream & s) const {
    Multimedia::show(s);
    s << "Durée : " << duree << std::endl;
}

void Video::play() const {
  std::string arg("mpv " + getPath()  + " &");
  system(arg.data());
}
