#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "link.h"


template <class T>
class LinkedList{
public:
  T get(int index);
  void add(T element);
  //void delete(int index);
private:
  Link<T>* head;
};


#endif
