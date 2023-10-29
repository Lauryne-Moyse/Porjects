
#include "Multimedia.h"




Multimedia::Multimedia(std::string const& name, std::string const& path){
      this->name = name;
      this->path = path;
}


void Multimedia::setName(std::string const& name) {
      this->name = name;
}


void Multimedia::setPath(std::string const& path){
      this->path = path;
}


std::string Multimedia::getName() const{
      return name;
}


std::string Multimedia::getPath() const{
      return path;
}


void Multimedia::show(std::ostream & s) const {
    s << "Name : " << name << std::endl
      << "Path : " << path << std::endl;
}
