
#ifndef Graph_Film
#define Graph_Film

#include "Video.h"
#import <iostream>
#import <string>
#import <array>


class Film : public Video {

private :
    int chap_nb{};
    int * chaps{};

public :
    Film(){}
    Film(std::string const& name, std::string const& path, int chap_nb, int * chaps);

    ~Film(){delete [] chaps;}

    int getChapNb() const {return chap_nb;}
    const int * getChaps() const {return chaps;}

    void setChaps(int chap_nb, int * chaps);

    void show(std::ostream & s) const final;

};

#endif
