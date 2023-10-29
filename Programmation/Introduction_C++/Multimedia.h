#ifndef Graph_Media
#define Graph_Media

#include <string>
#include <iostream>

class Multimedia {


protected :

    std::string name{};
    std::string path{};

public:

    Multimedia(){}
    Multimedia(std::string const& name, std::string const& path);

    virtual ~Multimedia(){}

    void setName(std::string const& name);
    void setPath(std::string const& path);
    std::string getName() const;
    std::string getPath() const;

    virtual void show(std::ostream & s) const;

    virtual void play() const = 0;

};

#endif
