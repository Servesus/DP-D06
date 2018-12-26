
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.FixUpTask;

@Component
@Transactional
public class FixUpTaskToStringConverter implements Converter<FixUpTask, String> {

	@Override
	public String convert(final FixUpTask fixUp) {
		String result;

		if (fixUp == null)
			result = null;
		else
			result = String.valueOf(fixUp.getId());

		return result;

	}

}
