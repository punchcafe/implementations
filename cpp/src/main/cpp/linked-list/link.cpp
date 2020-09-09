template <typename T>
class Link {
public:
  T* getElement();
  Link* getNextLink();
  Link* getPreviousLink();
  void setNextLink(Link* nextLink);
  void setPreviousLink(Link* nextLink);

private:
  T* element;
  Link* previousLink;
  Link* nextLink;
};
