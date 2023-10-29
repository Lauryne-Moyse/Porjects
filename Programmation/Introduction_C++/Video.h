
#ifndef Graph_Video
#define Graph_Video

#include <iostream>
#include "Multimedia.h"


class Video : public Multimedia {

protected:
    int duree{};

public:
    Video(){}

    Video(std::string const& name, std::string const& path, int duree) :
      Multimedia(name, path), duree(duree){}

    ~Video(){}

    void setDuree(int duree) {this->duree = duree;}
    const unsigned int getDuree() const {return duree;}

    void show(std::ostream & s) const override {
        Multimedia::show(s);
        s << "Durée : " << duree << std::endl;
    }

    void play() const final {
      std::string arg("mpv " + getPath()  + " &");
      system(arg.data());
    }


};

#endif
