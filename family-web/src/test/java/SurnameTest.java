import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

public class SurnameTest {

	@Test
	public void output() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\workspace\\Java\\family-parent\\doc\\surname.txt"), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\workspace\\Java\\family-parent\\doc\\surname_output.txt"), "UTF-8"));
			String line = null;
			Set<String> set = Sets.newLinkedHashSet();
			while((line = br.readLine()) != null) {
				if(StringUtils.isNotBlank(line.trim())) {
					Iterator<String> it = Splitter.on(" ").split(line).iterator();
					while(it.hasNext()) {
						String surname = it.next();
						if(StringUtils.isNotBlank(surname.trim())) {
							set.add(surname);
						}
					}
				}
			}
			Iterator<String> it = set.iterator();
			while(it.hasNext()) {
				bw.append(it.next());
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch(Throwable t) {
			
		}
	}
}
