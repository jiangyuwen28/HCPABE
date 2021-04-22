import com.njupt.operation.ElementG1;
import com.njupt.operation.ElementZr;

/**
 * MKi:私钥结构
 * @author 蒋余文
 *
 */
public class MKi {
	private ElementG1 d;
	private ElementG1[] Dij;
	private ElementG1[] Dij1;
	private ElementG1[] Ei;
	private ElementZr r;
	private KeyStructure keystructure;
	public ElementG1 getD() {
		return d;
	}
	public void setD(ElementG1 d) {
		this.d = d;
	}
	public ElementG1[] getDij() {
		return Dij;
	}
	public void setDij(ElementG1[] dij) {
		Dij = dij;
	}
	public ElementG1[] getDij1() {
		return Dij1;
	}
	public void setDij1(ElementG1[] dij1) {
		Dij1 = dij1;
	}
	public ElementG1[] getEi() {
		return Ei;
	}
	public void setEi(ElementG1[] ei) {
		Ei = ei;
	}
	public KeyStructure getKeystructure() {
		return keystructure;
	}
	public void setKeystructure(KeyStructure keystructure) {
		this.keystructure = keystructure;
	}
	public ElementZr getR() {
		return r;
	}
	public void setR(ElementZr r) {
		this.r = r;
	}
	
	
	
}
