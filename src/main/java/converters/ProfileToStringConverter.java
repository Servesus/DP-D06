
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Profile;

@Component
@Transactional
public class ProfileToStringConverter implements Converter<Profile, String> {

	@Override
	public String convert(final Profile p) {
		String result;

		if (p == null)
			result = null;
		else
			result = String.valueOf(p.getId());

		return result;
	}
}
