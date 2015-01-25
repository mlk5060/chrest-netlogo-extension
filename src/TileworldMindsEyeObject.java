import jchrest.lib.TwoDimensionalMindsEyeObject;
/**
 *
 * @author martyn
 */
public final class TileworldMindsEyeObject implements TwoDimensionalMindsEyeObject {
  
  private String _identifier;
  private int _xcor;
  private int _ycor;
  private int _terminus;
  
  public TileworldMindsEyeObject(String objectIdentifier, int xcor, int ycor){
    this.instantiateObject(objectIdentifier, xcor, ycor);
  }
  
  @Override
  public void instantiateObject(String string, int xcor, int ycor) {
    this._identifier = string;
    this._xcor = xcor;
    this._ycor = ycor;
  }

  @Override
  public int getDomainSpecificXCor() {
    return this._xcor;
  }

  @Override
  public int getDomainSpecificYCor() {
    return this._ycor;
  }

  @Override
  public String getIdentifier() {
    return this._identifier;
  }

  @Override
  public int getTerminus() {
    return this._terminus;
  }
  
  @Override
  public void setTerminus(int currentDomainTime){
    //Not sure how to do this yet.
  }
}
