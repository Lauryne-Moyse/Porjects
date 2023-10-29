#include <list>
#include <string>


std::list<> class Group : public std::list {

private :
    std::string group_name {};
    std::list<> group{};

public :
    Group(){}
    Group(std::string const& group_name, std::list<> const& group) :
      group_name(group_name), group(group) {}

    const std::string getGroup const {return groupe_name;}

    void show(std::ostream & s) const {
        for (auto i : group) i->show(s);
    }
}
