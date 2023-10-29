
#include "Film.h"


//On suppose que l'utilisateur est écalairé et que chap_nb correpond bien à la taille de chaps
Film::Film(std::string const& name, std::string const& path, int chap_nb, int * new_chaps) {
      this->name = name; this->path = path;
      if (new_chaps!=nullptr) {
        int duree = 0;
        this->chap_nb = chap_nb;
        chaps = new int[chap_nb];
        for (int i = 0; i < chap_nb; i++)  {chaps[i] = new_chaps[i]; duree = duree + new_chaps[i];}
        this->duree = duree;      }
}

void Film::setChaps(int chap_nb, int * new_chaps) {
  if (new_chaps!=nullptr) {
    this->chap_nb = chap_nb;
    chaps = new int[chap_nb];
    for (int i = 0; i < chap_nb; i++)  chaps[i] = new_chaps[i];
                            }
}


void Film::show(std::ostream & s) const {
    Multimedia::show(s);
    s << "Durée totale : " << duree << "\n" << "Durée des chapitres : " << "\n";
    for (int i = 0; i < chap_nb; i++) s << chaps[i] << "\n";
}
