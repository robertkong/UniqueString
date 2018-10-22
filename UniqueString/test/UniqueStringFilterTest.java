import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class UniqueStringFilterTest {

	@Test
	public void testReturnUniqueStrings() {
		String[] input =  {" Is it Sunny", " Sunny it is", "Hello World", "Hello World", "hello world"};
		UniqueStringFilter filter = new UniqueStringFilter();
		List<String> result = filter.returnUniqueStrings(Arrays.asList(input));
		System.out.println(result);

		String[] expectedResults = {" Sunny it is", "hello world"};
		Assert.assertArrayEquals("Result does not match!", expectedResults, result.toArray());
	}

}
