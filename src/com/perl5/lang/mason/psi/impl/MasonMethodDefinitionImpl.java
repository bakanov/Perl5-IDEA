/*
 * Copyright 2016 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.mason.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.perl5.lang.mason.psi.MasonMethodDefinition;
import com.perl5.lang.perl.idea.stubs.subsdefinitions.PerlSubDefinitionStub;
import com.perl5.lang.perl.psi.PerlVariable;
import com.perl5.lang.perl.psi.impl.PsiPerlMethodDefinitionImpl;
import com.perl5.lang.perl.psi.utils.PerlVariableType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Created by hurricup on 08.01.2016.
 */
public class MasonMethodDefinitionImpl extends PsiPerlMethodDefinitionImpl implements MasonMethodDefinition
{
	public static final String DEFAULT_INVOCANT_NAME = "self";

	public MasonMethodDefinitionImpl(ASTNode node)
	{
		super(node);
	}

	public MasonMethodDefinitionImpl(PerlSubDefinitionStub stub, IStubElementType nodeType)
	{
		super(stub, nodeType);
	}

	@Override
	public boolean isKnownVariable(@NotNull PerlVariable variable)
	{
		return variable.getActualType() == PerlVariableType.SCALAR && getDefaultInvocantName().equals(variable.getName());
	}

	@NotNull
	@Override
	public List<String> getFullQualifiedVariablesList()
	{
		return Collections.singletonList("$" + getDefaultInvocantName());
	}

	@NotNull
	@Override
	public String getDefaultInvocantName()
	{
		return DEFAULT_INVOCANT_NAME;
	}

}
