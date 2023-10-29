//
// main.cpp
// Created on 21/10/2018
//

#include "Multimedia.h"
#include "Video.h"
#include "Film.h"
#import <array>
#import <string>
#import <iostream>

int main(int argc, const char* argv[]) {
    int * chaps = new int[5]{1, 2, 3, 4, 5};
    Film * film = new Film("Hello", "chemin.mp4", 5, chaps);
    delete [] chaps;
    film->show(std::cout);
    return 0;
}

/*
std::vector<Multimedia> liste = std::vector<Multimedia>();
liste.push_back(new Photo("photo","received_2090835217770279.jpeg", 1, 2));
liste.push_back(new Video("video","realshort.mp4", 1));
for (auto & it : liste) it.show(std::cout);
*/
